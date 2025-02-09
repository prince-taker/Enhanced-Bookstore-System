class Name implements Printable
{
    private static final int MAX_FIRST_LEN = 50;
    private static final int MAX_LAST_LEN  = 50;

    private final String first;
    private final String last;

    Name(final String first,
         final String last)
    {
        if(first == null || first.isBlank() || first.length() >= MAX_FIRST_LEN)
        {
            throw new IllegalArgumentException("Invalid first name provided: " + first);
        }

        if(last == null || last.isBlank() || last.length() >= MAX_LAST_LEN)
        {
            throw new IllegalArgumentException("Invalid last name provided: " + last);
        }

        this.first = first;
        this.last = last;
    }

    @Override
    public void display()
    {
        System.out.println(first + " " + last);
    }

    public final String getFirst()
    {
        return first;
    }

    public final String getLast()
    {
        return last;
    }

    @Override
    public String toString() {
        return "Name{" + "first='" + first + '\'' + ", last='" + last + '\'' + '}';
    }
}
