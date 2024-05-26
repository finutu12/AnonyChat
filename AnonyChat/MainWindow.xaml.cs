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


namespace AnonyChat
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static readonly HttpClient client = new HttpClient();
        public MainWindow()
        {
            InitializeComponent();
            Window1 window1 = new Window1();
            window1.ShowDialog();
            
            if (window1.DialogResult == true)
            {
                usernameLabel.Content = Settings.Default.username;
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
        private void LeaveChatButton(object sender, RoutedEventArgs e)
        {
            findChatButton.Visibility = Visibility.Visible;
            leaveChatButton.Visibility = Visibility.Hidden;
        }
        private async void getFreeChatSession()
        {
            string apiUrl = "http://localhost:8080/chat/freeSession";
            TextBlock textBlock = new TextBlock();
            messageList.Children.Add(textBlock);

            try
            {
                ChatSession chatSession = await SendPostRequestAsync(Settings.Default.userID);
                textBlock.Text = "You have been connected to: " + chatSession.Name;
                textBlock.Foreground = Brushes.White;
                Settings.Default.chatID = chatSession.Id;
                Settings.Default.Save();
            }
            catch (Exception ex)
            {
                textBlock.Text = $"Error: {ex.Message}";
            }
        }
        private async Task<string> FetchDataFromApi(string url)
        {
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            return responseBody;
        }

        private async Task<ChatSession> SendPostRequestAsync(int userId)
        {
            string apiUrl = "http://localhost:8080/chat/freeSession";

            // Create JSON payload
            var content = new StringContent(JsonSerializer.Serialize(userId), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            // Deserialize the response to User object
            ChatSession chatSession = await response.Content.ReadFromJsonAsync<ChatSession>();
            return chatSession;
        }

        private async void SendButton(object sender, RoutedEventArgs e)
        {
            String temp = MessageTextBox.Text;
            Message message = await SendMessage(new Message(temp, new User(Settings.Default.userID), new ChatSession(Settings.Default.chatID)));
            TextBlock textBlock = new TextBlock();
            textBlock.Text = message.content;
            messageList.Children.Add(textBlock);
        }
        private async Task<Message> SendMessage(Message message)
        {
            string apiUrl = "http://localhost:8080/message/sendMessage";

            // Create JSON payload
            var content = new StringContent(System.Text.Json.JsonSerializer.Serialize(message), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            // Deserialize the response to User object
            message = await response.Content.ReadFromJsonAsync<Message>();
            return message;
        }
    }
}
