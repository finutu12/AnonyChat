   M S B A M L   `   `   `  ����D  @AnonyChat, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null%clr-namespace:AnonyChat	AnonyChat  5   	       AnonyChat.MainWindow   5   	   W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   )localclr-namespace:AnonyChat     5   	   -   5          � Icon$  Assets\Icon.png��5   	    � Title$ 	AnonyChat��5	   	   $	��450��6   $	��800��6(   $	��350��64   $	��500��6D   ��5
      �� 5   
    �� 
StartPoint$	 0,0&�6    �� EndPoint$	 1,1&�6/   .��5      �  � Color$ #1E1E1E��6    � Offset$ 0Q�6+   � 5      $ #3E3E3E��6   $ 1Q�6+   5      .��5      �  � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $��* �6   �� 5      $
��Auto �6    � ColumnDefinitions 5   
   �� 5      $��* �6   �� 5      $
��Auto �6   .+�5   
   � $��0��6   $��2��6   ���#2E2E2E$��50��6E   .+�5      �� -   5         ��Name$ usernameTextBlock��5      $��	AnonyChat��63   $��20(�6D   
�������$��Center=�6e   $��10,0,0,0q�6�   �� 5      -   5        	 ��Name$	 leaveChatButton��5      $y�Hidden=�6.   $��Right=�6B   $��
Leave Chat��6^   $��5q�6s   $	��100��6~   ���#F44336
�������6�   5       �� 5#   
   $��1��6   $��2��6$   $��10q�68   $
��Auto=�6D   .��5$      �� -   5        
 ��Name$
 messageList��5$      ���#1E1E1E$��10q�6C   .+�5&      �� -   5        $ messageGreet��5&      $
��Bold"�62   $��15(�6D   $ ��Press "Find Chat" to begin��6R   ���#CCCCCC$��5q�6�   5(      5)      � 5,   
   $��2��6   $��0��6   $��2��6,   ���#2E2E2E$
��Auto��6U    5-      �� 5.      $��* �6#   �� 5/      $
��Auto �6#   .+�51      �� -   5         ��Name$ MessageTextBox��51      $��0��6.   $��
10,10,5,10q�6>   ���#333333
�������$��30��6z   �� 52      -   5        $	 findChatButton��52      $��	Find Chat��6-   $��
5,10,10,10q�6A   $	��100��6U   ���#4CAF50
�������$��1��6�    K� ZIndex$ 1��6�   �� 53      -   5        $
��Send��53      $��1��6$   $	��100��64   $��
5,10,10,10q�6@   ���#2196F3
�������6|   54      56      57      