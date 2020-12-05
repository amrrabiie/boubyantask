package androiddev.amrrabie.boubyantask;

import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import androiddev.amrrabie.boubyantask.ui.DetailsActivity;
import androiddev.amrrabie.boubyantask.ui.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DetailsActivityTest {
    @Rule
    public ActivityScenarioRule<DetailsActivity> mActivityTestRule = new ActivityScenarioRule<DetailsActivity>(DetailsActivity.class);

    private DetailsActivity mActivity = null;

    /*@Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }*/

    @Test
    public void testLaunch(){
        View v=mActivity.findViewById(R.id.title);
        assertNotNull(v);
    }

    @Test
    public void testLaunch2(){
        View v=mActivity.findViewById(R.id.img);
        assertNotNull(v);
    }

    @Test
    public void testLaunch3(){
        View v=mActivity.findViewById(R.id.details);
        assertNotNull(v);
    }

    @Test
    public void checkNullTitle(){
        TextView v=mActivity.findViewById(R.id.title);
        String texttest=v.getText().toString();
        //String texttest=null;
        assertNull("Text Null Validity Test failed for %s ",texttest);
    }

    @Test
    public void checkNullDetails(){
        TextView v=mActivity.findViewById(R.id.details);
        String texttest=v.getText().toString();
        //String texttest=null;
        assertNull("Text Null Validity Test failed for %s ",texttest);
    }

}
