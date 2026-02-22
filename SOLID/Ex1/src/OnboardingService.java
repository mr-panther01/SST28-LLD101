import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();
    private final OnboardingUI ui = new OnboardingUI();

    public OnboardingService(StudentRepository db) {
        this.db = db;
    }

    public void registerFromRawInput(String raw) {
        ui.printInput(raw);

        // 1. Parsing
        Map<String, String> data = parser.parse(raw);

        // 2. Validation
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            ui.printErrors(errors);
            return;
        }

        // 3. ID Generation
        String id = IdUtil.nextStudentId(db.count());

        // 4. Creation & Persistence
        StudentRecord rec = new StudentRecord(
                id,
                data.get("name"),
                data.get("email"),
                data.get("phone"),
                data.get("program")
        );
        db.save(rec);

        // 5. Output
        ui.printSuccess(rec, db.count());
    }
}