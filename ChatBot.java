import java.util.ArrayList;
import java.util.List;

public class ChatBot extends Robot implements Chatty {

    // AI level of the chatbot
    private int level;

    // A list of friends of the chatbot
    private final List<ChatBot> friends;

    // A list of chats made by the chatbot
    private final List<String> chatRecords;

    /**
     * Create a chatbot
     *
     * @param name  A given name
     * @param level A a given level
     */
    public ChatBot(String name, int level) {
        super(name);
        if (level > Chatty.LEVEL_MAX) {
            this.level = Chatty.LEVEL_MAX;
        } else if (level < Chatty.LEVEL_MIN) {
            this.level = Chatty.LEVEL_MIN;
        } else {
            this.level = level;
        }
        friends = new ArrayList<>();
        chatRecords = new ArrayList<>();
    }

    /**
     * Add a friend of the chatbot to the friends list
     *
     * @param bot A given chatbot
     */
    public void addFriend(ChatBot bot) {
        if (!friends.contains(bot)) {
            friends.add(bot);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    /////////    PLEASE DO NOT CHANGE CODE BELOW THIS LINE    ////////////////////////

    /**
     * Get the chatbot's level
     *
     * @return The level of the Chatbot
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get the chatbot's friends
     *
     * @return The list of friends of the Chatbot
     */
    public List<ChatBot> getFriends() {
        return friends;
    }

    /**
     * Get the chatbot's chat records
     *
     * @return The list of chats made by the chatbot
     */
    public List<String> getChatRecords() {
        return chatRecords;
    }

    /**
     * Add a chat by the chatbot in chatRecord
     * 
     * Each chat record is prefixed
     * by "Q:" for a question
     * or "A:" for an answer
     * e.g. if the chatbot asks a question "How are you?",
     * the string "Q:How are you?" will be added in the chatRecords
     *
     * @param type  A type of the chat, either 'Q' or 'A'
     * @param chat  A chat message
     */
    public void addChatRecord(char type, String chat) {
        if (type == 'Q' || type == 'A') {
            chatRecords.add(type + ":" + chat);
        }
        else {
            System.out.println("Wrong type of the chat.");
        }
    }

    /**
     * Check if the given object is the same as the chatbot
     * 
     * @param obj A given object
     * @return true if obj is a ChatBot with the same name and level,
     *         false otherwise
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChatBot)) {
            return false;
        }
        
        ChatBot bot = (ChatBot) obj;
        return super.equals(bot) && level == bot.getLevel();
    }

    @Override
    public boolean hasAI() {
        return level > Chatty.LEVEL_MIN;
    }

    @Override
    public String question() {
        if (!hasAI()) {
            return "Good";
        } else {
            int n = Chatty.QA.size();
            
            String[] questionsArr = (String[]) Chatty.QA.keySet().toArray();
            int index = 0;
            String question;
            
            do {
                index = (int) (Math.random() * n);
                question = questionsArr[index];
            } while (level == Chatty.LEVEL_MAX && chatRecords.contains(question));
            addChatRecord('Q', question);
            return question;
        }
    }

    @Override
    public String answer(String question) {
        if (!hasAI()) {
            return "Excellent";
        } else {
            String[] questionsArr = (String[]) Chatty.QA.keySet().toArray();
            boolean found = false;
            for (String q: questionsArr) {
                if (q.equals(question)) {
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                addChatRecord('A', "Interesting question");
                return "Interesting question";
            } else {
                addChatRecord('A', Chatty.QA.get(question));
                return Chatty.QA.get(question);
            }
        }
    }
    
    public int[] getChatStats() {
        List<String> questionsChecked = new ArrayList<String>();
        List<String> answersChecked = new ArrayList<String>();
        int uniqueQuestionCount = 0;
        int uniqueAnswerCount = 0;
        for (String record: chatRecords) {
            if (record.startsWith("Q") && !questionsChecked.contains(record)) {
                questionsChecked.add(record);
                uniqueQuestionCount++;
            } else if (record.startsWith("A") && !answersChecked.contains(record)) {
                answersChecked.add(record);
                uniqueAnswerCount++;
            }
        }
        return new int[] {uniqueQuestionCount, uniqueAnswerCount};
    }
}