import java.util.HashMap;
import java.util.Map;

/**
 * The Chatty interface
 *
 * @author Yang He
 * @version 2023
 */
public interface Chatty {

    // Chat levels
    int LEVEL_MIN = 1;
    int LEVEL_MAX = 3;

    // Question & answer repertoires
    Map<String,String> QA = Map.of(
            "Hello, how are you?", "I'm great, thanks.",
            "What are you?", "I am a chatbot",
            "Do you have a hobby?", "I like chatting with you.",
            "Can you tell a joke?", "You are very funny!",
            "What is the capital of UK?", "London",
            "Do you like Java?", "Yes of course.",
            "Are you a robot?", "Yes I’m a robot, but I’m a smart one!",
            "Do you get smarter?", "I hope so."
    );

    /**
     * Ask a question
     *
     * @return the question
     */
    String question();

    /**
     * Answer a given question
     *
     * @param question A given question
     * @return An answer
     */
    String answer(String question);

}