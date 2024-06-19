using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnonyChat.Model
{
    internal class LeaveChatRequestBody
    {
        public User user { get; set; }
        public ChatSession chatSession { get; set; }

        public LeaveChatRequestBody(User user, ChatSession chatSession)
        {
            this.user = user;
            this.chatSession = chatSession;
        }
        public LeaveChatRequestBody()
        {

        }
    }
}
