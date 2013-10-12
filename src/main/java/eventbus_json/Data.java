package eventbus_json;

/**
 * @author bzd
 */
public class Data {
    private String name;
    private Integer age;

//    public Data() {
//    }

    public Data(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "dr " + name + "[" + age + "]";
    }
}
