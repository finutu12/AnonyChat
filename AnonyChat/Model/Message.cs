using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnonyChat.Model
{
    class Message
    {
        public int id { get; set; }
        public String content { get; set; }
        public DateTime timestamp = DateTime.Now ;
        public User user { get; set; }
        public ChatSession chatSession { get; set; }

        public Message(string content, User user, ChatSession chatSession)
        {
            this.content = content;
            this.user = user;
            this.chatSession = chatSession;
        }
        public Message()
        {

        }

    }
}
