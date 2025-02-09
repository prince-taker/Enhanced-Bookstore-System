class Autobiography extends Biography implements Printable
{
    Autobiography(String title, int yearPublished, Author author)
    {
        super(title, yearPublished, author, author);
    }

    @Override
    public void display()
    {
        System.out.println("title=" + super.getTitle() +
                ", yearPublished=" + super.getYearPublished() +
                ", author='" + super.getAuthor() +
                ", subject=" + super.getSubject());
    }


}
