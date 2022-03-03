package sample.model;

import java.io.Serializable;
import java.util.Objects;


public class Fiction extends Book implements Serializable {

    private String mGenre;

    public Fiction(String title, String authorLast, String authorFirst, String genre) {
        super(title, authorLast, authorFirst);
        mGenre = genre;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String newGenre) {
        mGenre = newGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fiction fiction = (Fiction) o;
        return Objects.equals(mGenre, fiction.mGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mGenre);
    }

    @Override
    public String toString() {
        String output = "";
        if (mBorrowed)
        {
            output = "Fiction[" +  "\"" +
                    mTitle + "\"; " +
                    mAuthorLast + ", " + mAuthorFirst + "; " +
                    mGenre + "; Borrowed by: " + mBorrower +
                    " on " + mDate + "]";
        }
        else{
            output = "Fiction[" +
                    "\"" + mTitle + "\"; " +
                    mAuthorLast + ", " + mAuthorFirst + "; " +
                    mGenre + "]";
        }

        return output;
    }


}

