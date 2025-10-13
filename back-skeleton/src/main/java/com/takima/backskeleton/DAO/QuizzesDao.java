import com.takima.backskeleton.models.Quiz;
import com.takima.backskeleton.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzesDao extends JpaRepository<Quiz, Long> {

    // Récupérer tous les quiz pour un sujet donné
    List<Quiz> findBySubject(Subject subject);

    // Ou par id de sujet
    List<Quiz> findBySubject_Id(Long subjectId);
}
