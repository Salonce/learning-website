package salonce.dev.todolist.course.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import salonce.dev.todolist.account.application.AccountService;
import salonce.dev.todolist.account.domain.Account;
import salonce.dev.todolist.account.infrastructure.security.AccountPrincipal;
import salonce.dev.todolist.course.domain.Course;
import salonce.dev.todolist.course.domain.Lesson;
import salonce.dev.todolist.course.infrastructure.LessonRepository;
import salonce.dev.todolist.course.presentation.LessonMapper;
import salonce.dev.todolist.course.presentation.dtos.LessonCreateRequest;
import salonce.dev.todolist.course.presentation.dtos.LessonViewResponse;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final AccountService accountService;
    private final CourseService courseService;
    private final LessonRepository lessonRepository;

    @Transactional
    public LessonViewResponse saveLesson(AccountPrincipal principal, Long courseId, LessonCreateRequest lessonCreateRequest){
        Account account = accountService.findAccount(principal.id());
        if (!account.isAdmin()) throw new AccessDeniedException("Access forbidden.");
        int nextOrderIndex = lessonRepository.findMaxOrderIndex(courseId) + 1;
        Course course = courseService.getCourseById(courseId);
        Lesson lesson = new Lesson(lessonCreateRequest.name(), nextOrderIndex);
        course.addLesson(lesson);
        return LessonMapper.toLessonViewResponse(lessonRepository.save(lesson));
    }
}
