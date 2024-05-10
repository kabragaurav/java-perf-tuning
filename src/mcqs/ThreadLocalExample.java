package mcqs;


public class ThreadLocalExample {
    // Create a ThreadLocal variable to store a user's session ID
    private static final ThreadLocal<String> userSessionId = new ThreadLocal<>();

    public static void main(String[] args) {
        // Simulate user login
        login("gaurav");

        // Access the user's session ID from any method
        System.out.println("Session ID for current thread: " + getSessionId());

        // Perform some operations...

        // Simulate user logout
        /**
         * Potential issue?
         * Ans.: If we do not log out, it will lead to Memory Leak issue
         */
        logout();

        // Attempt to access session ID after logout
        System.out.println("Session ID after logout: " + getSessionId());
    }

    public static void login(String userId) {
        // Generate and set a session ID for the current thread
        String sessionId = generateSessionId();
        userSessionId.set(sessionId);
        System.out.println("User " + userId + " logged in with session ID: " + sessionId);
    }

    public static void logout() {
        // Remove the session ID associated with the current thread
        userSessionId.remove();
        System.out.println("User logged out");
    }

    public static String getSessionId() {
        // Retrieve the session ID associated with the current thread
        return userSessionId.get();
    }

    public static String generateSessionId() {
        // Generate a session ID (for demonstration purposes)
        return "SessionID-" + Math.random();
    }
}

/*
Output:
User gaurav logged in with session ID: SessionID-0.5037495067760022
Session ID for current thread: SessionID-0.5037495067760022
User logged out
Session ID after logout: null

Explanation:
ThreadLocal is useful for scenarios where you need to store thread-specific data, such as user sessions, transaction contexts, or database connections, without the need for synchronization.
The logout method removes the session ID associated with the current thread using userSessionId.remove().
Each thread has its own copy of the userSessionId variable, and changes made by one thread do not affect other threads.
 */
