package com.gameplaycoder.cartrell.gamesquiz;

import com.gameplaycoder.cartrell.gamesquiz.databinding.ActivityMainBinding;

/**
 * This class is "The Grader". It's checks for all correct answers and returns the number
 * correct.
 */
class TheGrader {

  //=========================================================================
  // members
  //=========================================================================
  //binding for shortcuts to all the views
  private ActivityMainBinding mBinding;

  //=========================================================================
  // ctor
  //=========================================================================
  TheGrader(ActivityMainBinding binding) {
    mBinding = binding;
  }

  //=========================================================================
  // package-private
  //=========================================================================

  //-------------------------------------------------------------------------
  // eval
  //-------------------------------------------------------------------------
  /**
   * Evaluates the quiz answers.
   * @return - Returns the number of correct answers.
   */
  int eval() {
    int numCorrect = 0;

    if (isQuestion1Correct()) {
      numCorrect++;
    }

    if (isQuestion2Correct()) {
      numCorrect++;
    }

    if (isQuestion3Correct()) {
      numCorrect++;
    }

    if (isQuestion4Correct()) {
      numCorrect++;
    }

    if (isQuestion5Correct()) {
      numCorrect++;
    }

    if (isQuestion6Correct()) {
      numCorrect++;
    }

    return(numCorrect);
  }

  //=========================================================================
  // private
  //=========================================================================

  //-------------------------------------------------------------------------
  // isQuestion1Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 1
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion1Correct() {
    return(mBinding.q1Correct.isChecked());
  }

  //-------------------------------------------------------------------------
  // isQuestion2Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 2
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion2Correct() {
    //several acceptable answers
    final String CORRECT_RESPONSES[] = new String[]{
      "pacman",
      "pac man",
      "pac-man"
    };

    //compare the edit text value against the acceptable answers
    String editTextValue = mBinding.edtQ2.getText().toString();
    for (String correctResponse : CORRECT_RESPONSES) {
      if (editTextValue.equalsIgnoreCase(correctResponse)) {
        //found a match - case doesn't matter - correct answer
        return(true);
      }
    }

    //no matches ):
    return(false);
  }

  //-------------------------------------------------------------------------
  // isQuestion3Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 3
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion3Correct() {
    return(mBinding.q3Correct.isChecked());
  }

  //-------------------------------------------------------------------------
  // isQuestion4Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 4
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion4Correct() {
    return(
      mBinding.q4Correct0.isChecked() &&
      mBinding.q4Correct1.isChecked() &&
      !mBinding.q4Incorrect0.isChecked() &&
      !mBinding.q4Incorrect1.isChecked());
  }

  //-------------------------------------------------------------------------
  // isQuestion5Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 5
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion5Correct() {
    return(mBinding.q5Correct.isChecked());
  }

  //-------------------------------------------------------------------------
  // isQuestion6Correct
  //-------------------------------------------------------------------------
  /**
   * Evaluates question 6
   * @return - true if the question was answered correctly
   */
  private boolean isQuestion6Correct() {
    return(
      mBinding.q6Correct0.isChecked() &&
      mBinding.q6Correct1.isChecked() &&
      mBinding.q6Correct2.isChecked() &&
      mBinding.q6Correct3.isChecked() &&
      !mBinding.q6Incorrect0.isChecked());
  }
}
