import java.util.*;

public class StudentValidator {
    private final List<String> allowedPrograms = Arrays.asList("CSE", "AI", "SWE");

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<>();

        if (data.getOrDefault("name", "").isBlank())
            errors.add("name is required");

        String email = data.getOrDefault("email", "");
        if (email.isBlank() || !email.contains("@"))
            errors.add("email is invalid");

        String phone = data.getOrDefault("phone", "");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!allowedPrograms.contains(data.getOrDefault("program", "")))
            errors.add("program is invalid");

        return errors;
    }
}