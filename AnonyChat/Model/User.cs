using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnonyChat.Model
{
    class User
    {
        public int id { get; set; }
        public String username { get; set; }
        public ChatSession chatSession { get; set; }

        public User()
        {
        }

        public User(int userID)
        {
            id = userID;
        }


    }
}
