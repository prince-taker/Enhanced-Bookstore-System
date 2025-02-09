class Book implements Comparable<Book>, Printable, Reversible
{
    private static final int MAX_VALID_TITLE_LEN = 100;
    private static final int MIN_VALID_YEAR      = 1;
    private static final int CURRENT_YEAR        = 2025;

    private final String title;
    private final int yearPublished;
    private final Author author;

    Book(final String title,
         final int yearPublished,
         final Author author)
    {
        if (title == null || title.isBlank() || title.length() >= MAX_VALID_TITLE_LEN)
        {
            throw new IllegalArgumentException("Invalid title provided: " + title);
        }

        if (yearPublished < MIN_VALID_YEAR || yearPublished > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Invalid year published provided: " + yearPublished);
        }

        if (author == null)
        {
            throw new IllegalArgumentException("Invalid author provided: " + author);
        }
        this.title = title;
        this.yearPublished = yearPublished;
        this.author = author;
    }

    @Override
    public void display()
    {
        System.out.println("title=" + title + ", yearPublished=" + yearPublished + ", author='" + author.toString());
    }

    @Override
    public void backward()
    {
        final StringBuilder str;
        str = new StringBuilder(title);

        System.out.println(str.reverse());
    }

    @Override
    public int compareTo(final Book o)
    {
        return this.yearPublished - o.getYearPublished();
    }

    public final String getTitle()
    {
        return title;
    }

    public final int getYearPublished()
    {
        return yearPublished;
    }

    public final Author getAuthor()
    {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", yearPublished=" + yearPublished + ", author=" + author + '}';
    }
}
