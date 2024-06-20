using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Net.Http;
using System.Threading.Tasks;
using System.Net.Http.Json;
using System.Text.Json;
using AnonyChat.Model;
using AnonyChat.Properties;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Media;
using System.ComponentModel;

namespace AnonyChat
{
    public partial class MainWindow : Window
    {
        private static readonly HttpClient client = new HttpClient();
        private List<Message> messagesList = new List<Message>();
        private bool leftChat = false;

        public MainWindow()
        {
            InitializeComponent();
            var window1 = new Window1();
            window1.ShowDialog();

            if (window1.DialogResult == true)
            {
                usernameTextBlock.Text = $"Welcome {Settings.Default.username}";
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
            await LeaveChat(user);
            leftChat = true;
        }

        private async Task LeaveChat(User user)
        {
            string apiUrl = "http://localhost:8080/chat/leave";
            var content = new StringContent(JsonSerializer.Serialize(user), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();
            this.addMessageToChat(new Message("You have left the chat."));
        }

        private void addMessageToChat(Message message, HorizontalAlignment alignment = HorizontalAlignment.Left)
        {
            TextBlock textBlock = new TextBlock
            {
                Text = message.content,
                Foreground = Brushes.Black,
                FontSize = 15,
                Margin = new Thickness(10),
            };
            Border messageBubble = new Border
            {
                CornerRadius = new CornerRadius(15),
                Padding = new Thickness(10),
                Margin = new Thickness(5),
                Child = textBlock
            };
            if (alignment == HorizontalAlignment.Left)
            {
                messageBubble.Background = Brushes.LightBlue;
            }
            else
            {
                messageBubble.Background = Brushes.LightGreen;
            }
            textBlock.HorizontalAlignment = alignment;
            messageList.Children.Add(messageBubble);
            messagesList.Add(message);
        }

        private async void getFreeChatSession()
        {
            try
            {
                ChatSession chatSession = await SendPostRequestAsync(Settings.Default.userID);
                Settings.Default.chatID = chatSession.Id;
                Settings.Default.Save();

                this.addMessageToChat(new Message($"You have been connected to: {chatSession.Name}"));
                await UpdateChat();
            }
            catch (Exception ex)
            {
                this.addMessageToChat(new Message($"Error: {ex.Message}"));
            }
        }

        async Task UpdateChat()
        {
            while (Settings.Default.chatID != 0)
            {
                try
                {
                    Console.WriteLine($"Function executed at: {DateTime.Now}");
                    List<Message> messages = await GetMessages(Settings.Default.chatID);
                    foreach (Message message in messages)
                    {
                        if (!messagesList.Any(m => m.id == message.id))
                        {
                           
                            this.addMessageToChat(message);
                                   
                        }
                    }

                    messagesList = messages;
                    await Task.Delay(2500);
                }
                catch (Exception ex)
                {
                    this.addMessageToChat(new Message($"Error while updating chat: {ex.Message}"));
                    break;
                }
            }
        }

        private async void Window_Closing(object sender, CancelEventArgs e)
        {
            if (leaveChatButton.Visibility == Visibility.Visible)
            {
                // Show a confirmation dialog
                var result = MessageBox.Show("You will be disconnected from the chat. Proceed?", "Confirm Close", MessageBoxButton.YesNo, MessageBoxImage.Question);

                if (result == MessageBoxResult.No)
                {
                    // Cancel the closing event
                    e.Cancel = true;
                }
                else
                {
                    User user = new User(Settings.Default.userID);
                    await LeaveChat(user);
                }
            }
        }

        private async Task<string> FetchDataFromApi(string url)
        {
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadAsStringAsync();
        }

        private async Task<List<Message>> GetMessages(int sessionId)
        {
            string apiUrl = "http://localhost:8080/message/getMessages";

            var content = new StringContent(JsonSerializer.Serialize(sessionId), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            return await response.Content.ReadFromJsonAsync<List<Message>>();
        }

        private async Task<ChatSession> SendPostRequestAsync(int userId)
        {
            string apiUrl = "http://localhost:8080/chat/freeSession";

            var content = new StringContent(JsonSerializer.Serialize(userId), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            return await response.Content.ReadFromJsonAsync<ChatSession>();
        }

        private async void SendButton(object sender, RoutedEventArgs e)
        {
            string sentMessage = MessageTextBox.Text.Trim();
            if (!string.IsNullOrEmpty(sentMessage))
            {
                Message message = await SendMessage(new Message(sentMessage, new User(Settings.Default.userID), new ChatSession(Settings.Default.chatID)));
                this.addMessageToChat(message, HorizontalAlignment.Right);
                MessageTextBox.Clear();
            }
        }

        private async Task<Message> SendMessage(Message message)
        {

            string apiUrl = "http://localhost:8080/message/sendMessage";
            int param1 = message.chatSession.Id;
            int param2 = message.user.id;
            string param3 = message.content;

            string url = $"{apiUrl}?chatSessionId={param1}&userId={param2}&messageContent={param3}";

            var content = new StringContent(JsonSerializer.Serialize(message), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();

            return await response.Content.ReadFromJsonAsync<Message>();
        }
    }
}
