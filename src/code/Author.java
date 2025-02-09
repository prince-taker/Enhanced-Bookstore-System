class Author extends Person implements Printable
{
    private static final int MAX_VALID_GENRE_LEN = 30;
    public final String genre;

    Author(final Date dateOfBirth,
           final Date dateOfDeath,
           final Name name,
           final String genre)
    {
        super(dateOfBirth, dateOfDeath, name);

        if(genre == null || genre.isBlank() || genre.length() >= MAX_VALID_GENRE_LEN)
        {
            throw new IllegalArgumentException("Invalid genre provided:" + genre);
        }

        this.genre = genre;
    }

    @Override
    public void display()
    {
        System.out.println("dateOfBirth=" + super.getDateOfBirth() +
                ", name=" + super.getDateOfBirth() +
                ", dateOfDeath=" + super.getDateOfBirth() +
                ", genre=" + genre);
    }

    @Override
    public String toString()
    {
        return "Author{" + "genre='" + genre + '\'' + '}';
    }
}
