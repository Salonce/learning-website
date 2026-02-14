package salonce.dev.website.course.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.course.application.CourseService;
import salonce.dev.website.course.presentation.dtos.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("permitAll()")
    @GetMapping("/api/courses")
    public ResponseEntity<List<CourseMetadataResponse>> getAllCourseViewResponses(){
        return ResponseEntity.ok(courseService.getAllCoursesMetadata());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/courses/slug/{slug}")
    public ResponseEntity<CourseResponse> getCourseViewResponse(@PathVariable String slug){
        return ResponseEntity.ok(courseService.getCourseBySlug(slug));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/courses/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseResponseById(id));
    }

    @PreAuthorize("hasAuthority('course:create')")
    @PostMapping("/api/courses")
    public ResponseEntity<CourseResponse> saveCourse(@RequestBody CourseCreateRequest courseCreateRequest){
        return ResponseEntity.ok(courseService.saveCourse(courseCreateRequest));
    }

    @PreAuthorize("hasAuthority('course:update')")
    @PatchMapping("/api/courses/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest courseUpdateRequest){
        return ResponseEntity.ok(courseService.updateCourse(id, courseUpdateRequest));
    }

    @PreAuthorize("hasAuthority('course:delete')")
    @DeleteMapping("/api/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('course:reorder')")
    @PutMapping("api/courses/positions")
    public ResponseEntity<Void> reorderCourses(@RequestBody ReorderRequest request) {
        courseService.reorderCourses(request);
        return ResponseEntity.ok().build();
    }
}
