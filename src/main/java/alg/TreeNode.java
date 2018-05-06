package alg;

public class TreeNode {
    Person person;

    public TreeNode left;
    public TreeNode right;

    protected TreeNode(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "(ID: " + person.id + "; Name: " +
                person.name + "; Age: " + person.age + ")";
    }
}
