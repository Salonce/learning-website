package salonce.dev.website.course.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.course.application.CourseService;
import salonce.dev.website.course.presentation.dtos.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LessonController {

    private final CourseService courseService;


    @GetMapping("/api/courses/slug/{courseSlug}/lessons")
    public ResponseEntity<List<LessonMetadataResponse>> getLessonsByCourseSlug(@PathVariable String courseSlug){
        List<LessonMetadataResponse> lessons = courseService.getLessonsMetadataByCourseSlug(courseSlug);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/api/courses/{courseId}/lessons")
    public ResponseEntity<List<LessonMetadataResponse>> getLessonsByCourseId(@PathVariable Long courseId){
        List<LessonMetadataResponse> lessons = courseService.getLessonsMetadataById(courseId);
        return ResponseEntity.ok(lessons);
    }

    @PostMapping("/api/courses/{courseId}/lessons")
    public ResponseEntity<LessonMetadataResponse> saveLesson(@PathVariable Long courseId, @RequestBody LessonCreateRequest lessonCreateRequest){
        return ResponseEntity.ok(courseService.saveLesson(courseId, lessonCreateRequest));
    }

    @GetMapping("/api/lessons/{id}")
    public ResponseEntity<LessonResponse> getLessonById(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable Long id){
        return ResponseEntity.ok(courseService.getLessonById(id));
    }

    @PatchMapping("/api/lessons/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long id, @RequestBody LessonUpdateRequest lessonUpdateRequest){
        return ResponseEntity.ok(courseService.updateLesson(id, lessonUpdateRequest));
    }

    @GetMapping("/api/courses/slug/{courseSlug}/lessons/slug/{lessonSlug}")
    public ResponseEntity<LessonResponse> getLessonBySlugs(@PathVariable String courseSlug, @PathVariable String lessonSlug){
        return ResponseEntity.ok(courseService.getLessonBySlugs(courseSlug, lessonSlug));
    }

    @DeleteMapping("/api/lessons/{id}")
    public ResponseEntity<Void> deleteLesson(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable Long id){
        courseService.deleteLesson(principal, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("api/lessons/positions")
    public ResponseEntity<Void> reorderLessons(@AuthenticationPrincipal AccountPrincipal principal, @RequestBody ReorderRequest reorderRequest) {
        courseService.reorderLessons(principal, reorderRequest);
        return ResponseEntity.ok().build();
    }

    // Blocks

    @GetMapping("/api/lessons/{lessonId}/contentblocks")
    public ResponseEntity<List<ContentBlockResponse>> getBlocksByLessonId(@PathVariable Long lessonId){
        List<ContentBlockResponse> blocks = courseService.getContentBlocksByLessonId(lessonId);
        return ResponseEntity.ok(blocks);
    }

    @PostMapping("/api/lessons/{lessonId}/contentblocks")
    public ResponseEntity<ContentBlockResponse> saveBlock(@PathVariable Long lessonId, @RequestBody ContentBlockCreateRequest contentBlockCreateRequest){
        ContentBlockResponse block = courseService.saveContentBlock(lessonId, contentBlockCreateRequest);
        return ResponseEntity.ok(block);
    }

    @DeleteMapping("/api/contentblocks/{blockId}")
    public ResponseEntity<Void> removeContentBlock(@PathVariable Long blockId){
        courseService.removeContentBlock(blockId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/api/contentblocks/{blockId}")
    public ResponseEntity<ContentBlockResponse> updateContentBlock(@PathVariable Long blockId, @RequestBody ContentBlockUpdateRequest updateRequest) {
        ContentBlockResponse response = courseService.updateContentBlock(blockId, updateRequest);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/api/courses/{courseId}/lessons")
//    public ResponseEntity<List<LessonMetadataResponse>> getLessonsByCourseId(@PathVariable Long courseId){
//        List<LessonMetadataResponse> lessons = courseService.getLessonsMetadataById(courseId);
//        return ResponseEntity.ok(lessons);
//    }
//
//    @PostMapping("/api/courses/{courseId}/lessons")
//    public ResponseEntity<LessonMetadataResponse> saveLesson(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable Long courseId, @RequestBody LessonCreateRequest lessonCreateRequest){
//        return ResponseEntity.ok(courseService.saveLesson(principal, courseId, lessonCreateRequest));
//    }
//
//    @DeleteMapping("/api/lessons/{id}")
//    public ResponseEntity<Void> deleteLesson(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable Long id){
//        courseService.deleteLesson(principal, id);
//        return ResponseEntity.noContent().build();
//    }
}
