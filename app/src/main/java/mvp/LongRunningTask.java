package mvp;

public class LongRunningTask {

    public String execute() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "wykonane";
    }

}
