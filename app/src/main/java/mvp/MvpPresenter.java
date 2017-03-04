package mvp;

import android.view.View;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import nucleus.presenter.Presenter;

public class MvpPresenter extends Presenter<MvpActivity> {

    private LongRunningTask longRunningTask = new LongRunningTask();

    public void executeLongRunningTask() {
        new Thread() {
            public void run() {
                final String result = longRunningTask.execute();
                getView().setTextOnUiThread(result);
            }
        }.start();
    }
}
