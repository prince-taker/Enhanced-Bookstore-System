class Person implements Printable, Reversible, Comparable<Person>
{
    private final Date dateOfBirth;
    private final Name name;
    private Date       dateOfDeath;

    Person(final Date dateOfBirth,
           final Date dateOfDeath,
           final Name name)
    {
        if(dateOfBirth == null)
        {
            throw new IllegalArgumentException("Invalid date of death provided: " + dateOfDeath);
        }

        if(name ==  null)
        {
            throw new IllegalArgumentException("Invalid name provided: " + name);
        }

        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.name        = name;
    }

    @Override
    public void display()
    {
        System.out.println("dateOfBirth=" + dateOfBirth + ", name=" + name + ", dateOfDeath=" + dateOfDeath);
    }

    @Override
    public void backward()
    {
        final StringBuilder str;
        str = new StringBuilder(name.getFirst() + " " + name.getLast());

        System.out.println(str.reverse());
    }

    public void setDateOfDeath(final Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

    public final Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public final Name getName()
    {
        return name;
    }

    public final Date getDateOfDeath()
    {
        return dateOfDeath;
    }

    @Override
    public int compareTo(final Person o)
    {
        return dateOfBirth.getYear() - o.dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        return "Person{" + "dateOfBirth=" + dateOfBirth + ", name=" + name + ", dateOfDeath=" + dateOfDeath + '}';
    }
}
