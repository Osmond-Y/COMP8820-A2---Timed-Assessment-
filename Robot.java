/**
 * The Robot class represents a robot.
 * It holds relevant details of a robot.
 *
 * @author Yang He
 * @version 2023
 */
public abstract class Robot {

    // the robot's name
    private String name;

    /**
     * Create a new robot with a given name
     *
     * @param name A given name
     */
    public Robot(String name) {
        this.name = name;
    }

    /**
     * Get the robot's name
     *
     * @return The robot's name
     */
    public String getName() {
        return name;
    }

    /**
     * Check if the robot has AI
     *
     * @return true if the robot has AI, 
     *         false otherwise
     */
    public abstract boolean hasAI();

    /**
     * @param  obj A given object
     * @return true if obj is a Robot with the same name as the robot,
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Robot)) {
            return false;
        }

        Robot bot = (Robot) obj;
        return name.equals(bot.getName());
    }    
}