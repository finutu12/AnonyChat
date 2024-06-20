using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net.Http;
using System.Threading.Tasks;
using AnonyChat.Model;
using System.Net.Http.Json;
using System.Text.Json;
using AnonyChat.Properties;
using System.Dynamic;
using Stomp.Net;
using System.Collections.ObjectModel;
using System.Net.WebSockets;
using System.Net;
using System.Runtime.InteropServices.JavaScript;
using System.Security.Policy;
using System.Threading;
using WebSocketSharp;
using Websocket.Client;
using System.Reactive.Linq;
using System.Runtime.CompilerServices;


namespace AnonyChat
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static readonly HttpClient client = new HttpClient();
        List<Message> ms = new List<Message>();
        private bool leftChat = false;

        public MainWindow()
        {
            InitializeComponent();
            Window1 window1 = new Window1();
            window1.ShowDialog();

            if (window1.DialogResult == true)
            {
                usernameTextBlock.Text = "Welcome " + Settings.Default.username;
            }
            else
            {
                MessageBox.Show("Dialog window was closed with Cancel.");
            }
           
        }

        private void FindChatButton(object sender, RoutedEventArgs e)
        {
            findChatButton.Visibility = Visibility.Hidden;
            leaveChatButton.Visibility = Visibility.Visible;
            getFreeChatSession();
        }
        private async void LeaveChatButton(object sender, RoutedEventArgs e)
        {
            findChatButton.Visibility = Visibility.Visible;
            leaveChatButton.Visibility = Visibility.Hidden;
            User user = new User(Settings.Default.userID);
            ChatSession chatSession = new ChatSession(Settings.Default.chatID);
           // Message message = await SendMessage(new Message("Partner left the chat :(", user, chatSession));
            //this.addMessageToChat(message.content);
            LeaveChat(user);
            leftChat = true;
        }

        private async void LeaveChat(User user)
        {
            string apiUrl = "http://localhost:8080/chat/leave";
            // Create JSON payload
            var content = new StringContent(System.Text.Json.JsonSerializer.Serialize(user), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();
        }

        private void addMessageToChat(string message)
        {
            TextBlock textBlock = new TextBlock();
            messageList.Children.Add(textBlock);
            textBlock.Text = message;
            textBlock.Foreground = Brushes.White;
        }

        private async void getFreeChatSession()
        {
            try
            {
                ChatSession chatSession = await SendPostRequestAsync(Settings.Default.userID);
                Settings.Default.chatID = chatSession.Id;
                Settings.Default.Save();
                if (chatSession.)
                    usernameTextBlock.Text = "";
                this.addMessageToChat("You have been connected to: " + chatSession.Name);
                await this.UpdateChat();
            }
            catch (Exception ex)
            {
                this.addMessageToChat($"Error: {ex.Message}");
            }
        }

        async Task UpdateChat()
        {

            while (Settings.Default.chatID != 0)
            {
                Console.WriteLine("Function executed at: " + DateTime.Now);
                List<Message> messages = await this.GetMessages(Settings.Default.chatID);
                foreach (Message message in messages)
                {
                    if(!this.ms.Any(obj => obj.id == message.id))
                    {
                        this.addMessageToChat(message.content);
                    }
                }

                this.ms = messages;

                await Task.Delay(5000);
            }
        }

        private async Task<string> FetchDataFromApi(string url)
        {
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            return responseBody;
        }

        private async Task<List<Message>> GetMessages(int sessionId)
        {
            string apiUrl = "http://localhost:8080/message/getMessages";

            // Create JSON payload
            var content = new StringContent(JsonSerializer.Serialize(sessionId), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            // Deserialize the response to User object
            List<Message> messages = await response.Content.ReadFromJsonAsync<List<Message>>();
            return messages;
        }

        private async Task<ChatSession> SendPostRequestAsync(int userId)
        {
            string apiUrl = "http://localhost:8080/chat/freeSession";

            var content = new StringContent(JsonSerializer.Serialize(userId), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            ChatSession chatSession = await response.Content.ReadFromJsonAsync<ChatSession>();
            return chatSession;
        }

        private async void SendButton(object sender, RoutedEventArgs e)
        {
            String sentMessage = MessageTextBox.Text;
            Message message = await SendMessage(new Message(sentMessage, new User(Settings.Default.userID), new ChatSession(Settings.Default.chatID)));
            this.addMessageToChat(message.content);
        }

        private async Task<Message> SendMessage(Message message)
        {
            string apiUrl = "http://localhost:8080/message/sendMessage";

            var content = new StringContent(System.Text.Json.JsonSerializer.Serialize(message), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            message = await response.Content.ReadFromJsonAsync<Message>();
            return message;
        }
    }
}
