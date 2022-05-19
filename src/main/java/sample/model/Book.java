package sample.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Book implements Comparable, Serializable {

    protected String mTitle;
    protected String mAuthorLast;
    protected String mAuthorFirst;
    protected boolean mBorrowed;
    protected String mBorrower;
    protected String mDate;


    public Book(String title, String authorLast, String authorFirst) {
        mTitle = title;
        mAuthorLast = authorLast;
        mAuthorFirst = authorFirst;
        mBorrowed = false;
        mBorrower = null;
        mDate = null;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        this.mTitle = newTitle;
    }

    public String getAuthorLast() {
        return mAuthorLast;
    }

    public void setAuthorLast(String newAuthorLast) {
        this.mAuthorLast = newAuthorLast;
    }

    public String getAuthorFirst() {
        return mAuthorFirst;
    }

    public void setAuthorFirst(String newAuthorFirst) {
        this.mAuthorFirst = newAuthorFirst;
    }

    public boolean isBorrowed() {
        return mBorrowed;
    }

    public void setBorrowed(boolean newBorrowed) {
        this.mBorrowed = newBorrowed;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String newDate) {
        this.mDate = newDate;
    }

    public String getBorrower() {
        return mBorrower;
    }

    public void setBorrower(String newBorrower) {
        this.mBorrower = newBorrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return mBorrowed == book.mBorrowed &&
                Objects.equals(mAuthorLast, book.mAuthorLast) &&
                Objects.equals(mAuthorFirst, book.mAuthorFirst) &&
                Objects.equals(mTitle, book.mTitle) &&
                Objects.equals(mDate, book.mDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mTitle, mAuthorLast, mAuthorFirst, mBorrowed, mDate);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Book){
            Book other = (Book) o;
            int lastNameComp = mAuthorLast.compareTo(other.mAuthorLast);
            if(lastNameComp != 0)
                return lastNameComp;
            int firstNameComp = mAuthorFirst.compareTo(other.mAuthorFirst);
            if(firstNameComp != 0)
                return firstNameComp;
            int titleComp = mTitle.compareTo(other.mTitle);
            if(titleComp != 0)
                return titleComp;

        }
        return 0;
    }
}

