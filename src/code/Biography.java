class Biography extends Book implements Printable
{
    private final Person subject;

    Biography(final String title,
              final int yearPublished,
              final Author author,
              final Person subject)
    {
        super(title, yearPublished, author);

        if(subject == null)
        {
            throw new IllegalArgumentException("Invalid subject provided: " + subject);
        }

        this.subject = subject;
    }

    public final Person getSubject()
    {
        return subject;
    }

    @Override
    public void display()
    {
        System.out.println("title=" + super.getTitle() +
                ", yearPublished=" + super.getYearPublished() +
                ", author='" + super.getAuthor() +
                ", subject=" + subject);
    }

    @Override
    public boolean equals(final Object that)
    {
        if(this == that)
        {
            return true;
        }

        if(that == null)
        {
            return false;
        }

        if(getClass() != that.getClass())
        {
            return false;
        }

        final Biography biography;
        biography = (Biography) that;
        return subject == biography.getSubject();
    }

    @Override
    public String toString()
    {
        return "Biography{" + "subject=" + subject + '}';
    }
}
