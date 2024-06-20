using AnonyChat.Model;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Text.Json;
using AnonyChat.Properties;

namespace AnonyChat
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class Window1 : Window
    {
        private static readonly HttpClient client = new HttpClient();
        public Window1()
        {
            InitializeComponent();
        }

        private async void AddUser(object sender, RoutedEventArgs e)
        {
            User user = new User();
            user.username = usernameTextBox.Text;
            user = await SendPostRequestAsync(user);
            Settings.Default.userID = user.id;
            Settings.Default.username = user.username;
            Settings.Default.Save();
            DialogResult = true;
            this.Close();
        }



        private async Task<User> SendPostRequestAsync(User user)
        {
            string apiUrl = "http://localhost:8080/user/getUser";

            // Create JSON payload
            var content = new StringContent(System.Text.Json.JsonSerializer.Serialize(user), Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(apiUrl, content);
            response.EnsureSuccessStatusCode();

            // Deserialize the response to User object
            user = await response.Content.ReadFromJsonAsync<User>();
            return user;
        }

        private void usernameTextBox_TextChanged(object sender, TextChangedEventArgs e)
        {
            if(usernameTextBox.Text.Length >= 3 && usernameTextBox.Text.Length <= 20)
            {
                RegisterButton.IsEnabled = true;
                if (TextLength is not null)
                {
                    TextLength.Visibility = Visibility.Hidden;
                }
            }
            else
            {
                RegisterButton.IsEnabled = false;
                if(TextLength is not null)
                {
                    TextLength.Visibility = Visibility.Visible;
                }
            }
        }
    }
}
