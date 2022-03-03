package sample.model;

import java.io.Serializable;
import java.util.Objects;

public class NonFiction extends Book implements Serializable {

    private String mSubject;

    public NonFiction(String title, String authorLast, String authorFirst, String subject) {
        super(title, authorLast, authorFirst);
        this.mSubject = subject;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String newSubject) {
        mSubject = newSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NonFiction that = (NonFiction) o;
        return Objects.equals(mSubject, that.mSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mSubject);
    }

    @Override
    public String toString(){
        String output = "";
        if (mBorrowed)
        {
            output = "NonFiction[" + "\"" +
                    mTitle + "\"; " +
                    mAuthorLast + ", " + mAuthorFirst + "; " +
                    mSubject + "; Borrowed by: " + mBorrower +
                    " on " + mDate + "]";
        }
        else{
            output = "NonFiction[" + "\"" +
                    mTitle + "\"; " +
                    mAuthorLast + ", " + mAuthorFirst + "; " +

                    mSubject + "]";
        }

        return output;
    }
}
