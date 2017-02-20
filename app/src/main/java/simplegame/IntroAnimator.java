package simplegame;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

public class IntroAnimator {

    private final TextView textView;

    public IntroAnimator(TextView textView){
        this.textView = textView;
    }

    public void showIntro(Runnable animationsEnds){

        animatedCounter(textView, animationsEnds, 3);
    }

    private void animatedCounter(final TextView startText, final Runnable animationsEnds, final int counter) {

        String text = counter == 0 ? "START" : String.valueOf(counter);

        startText.setText(text);
        startText.setAlpha(1);
        startText.setScaleX(1);
        startText.setScaleY(1);
        startText.animate()
                .alpha(0)
                .scaleX(2)
                .scaleY(2)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (counter > 0) {
                            animatedCounter(startText, animationsEnds, counter - 1);
                        } else {
                            animationsEnds.run();
                        }
                    }
                })
                .start();
    }

}
