package com.gameplaycoder.cartrell.gamesquiz;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gameplaycoder.cartrell.gamesquiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  //=========================================================================
  // members
  //=========================================================================
  private ActivityMainBinding mBinding;

  //=========================================================================
  // public
  //=========================================================================

  //-------------------------------------------------------------------------
  // onResetClick
  //-------------------------------------------------------------------------
  /**
   * Clears all the input made by the user.
   * @param view - The view that trigger this callback.
   */
  public void onResetClick(View view) {
    //reset question 1
    mBinding.radioGroupQ1.clearCheck();

    //reset question 2
    mBinding.edtQ2.setText("");
    mBinding.edtQ2.clearFocus();

    //reset question 3
    mBinding.radioGroupQ3.clearCheck();

    //reset question 4
    mBinding.q4Correct0.setChecked(false);
    mBinding.q4Correct1.setChecked(false);
    mBinding.q4Incorrect0.setChecked(false);
    mBinding.q4Incorrect1.setChecked(false);

    //reset question 5
    mBinding.radioGroupQ5.clearCheck();

    //reset question 6
    mBinding.q6Correct0.setChecked(false);
    mBinding.q6Correct1.setChecked(false);
    mBinding.q6Correct2.setChecked(false);
    mBinding.q6Correct3.setChecked(false);
    mBinding.q6Incorrect0.setChecked(false);
  }

  //-------------------------------------------------------------------------
  // onSubmitClick
  //-------------------------------------------------------------------------
  /**
   * Checks the user's input and displays the number of correct answers.
   * @param view - The view that trigger this callback.
   */
  public void onSubmitClick(View view) {
    TheGrader theGrader = new TheGrader(mBinding);
    int numCorrect = theGrader.eval();

    String messageFormat = getString(R.string.scoreMessageFormat);
    String message = String.format(messageFormat, numCorrect, numCorrect != 1 ? "s" : "");

    Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    toast.show();
  }

  //=========================================================================
  // protected
  //=========================================================================

  //-------------------------------------------------------------------------
  // onCreate
  //-------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    setFontForAllCheckBoxes();
  }

  //=========================================================================
  // private
  //=========================================================================

  //-------------------------------------------------------------------------
  // isCheckBoxContainer
  //-------------------------------------------------------------------------
  /**
   * Helper function that determined if a view is a checkbox container, depending on how
   * the view was setup in the XML layout.
   * @param view - The view being tested.
   * @return - True if the view is a checkbox container
   */
  private boolean isCheckBoxContainer(View view) {
    if (!(view instanceof ViewGroup)) {
      return(false);
    }

    //all checkbox containers should have an appropriate tag set.
    Object tag = view.getTag();
    if (tag instanceof String) {
      String sTag = (String)tag;
      String checkBoxContainerTag = getString(R.string.checkBoxContainerTag);
      return(sTag.equalsIgnoreCase(checkBoxContainerTag));
    }

    return(false);
  }

  //-------------------------------------------------------------------------
  // setFontForAllCheckBoxes
  //-------------------------------------------------------------------------
  /**
   * Iterates all the child views of the content layout view and changes the fonts
   * of all the check boxes. Unfortunately, this applying the font via XML doesn't seem to work.
   */
  private void setFontForAllCheckBoxes() {
    ViewGroup viewGroup = findViewById(R.id.lytContent);
    int numChildren = viewGroup.getChildCount();
    for (int childIndex = 0; childIndex < numChildren; childIndex++) {
      View childView = viewGroup.getChildAt(childIndex);
      if (isCheckBoxContainer(childView)) {
        setFontForAllCheckBoxesIn((ViewGroup)childView);
      }
    }
  }

  //-------------------------------------------------------------------------
  // setFontForAllCheckBoxesIn
  //-------------------------------------------------------------------------
  /**
   * Iterates all the child views of the content layout view and changes the fonts
   * of all the check boxes. Unfortunately, applying the font via XML doesn't seem to work.
   * @param viewGroup - The group that contains the checkboxes.
   */
  private void setFontForAllCheckBoxesIn(ViewGroup viewGroup) {
    Typeface font = Typeface.createFromAsset(getAssets(), "modenine.ttf");
    int numChildren = viewGroup.getChildCount();
    for (int childIndex = 0; childIndex < numChildren; childIndex++) {
      View childView = viewGroup.getChildAt(childIndex);
      if (childView instanceof CheckBox) {
        CheckBox checkBox = (CheckBox)childView;
        checkBox.setTypeface(font);
      }
    }
  }
}
