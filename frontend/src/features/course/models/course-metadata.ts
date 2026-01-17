import { LessonMetadata } from "./lesson-metadata";

export interface CourseMetadata{
  id : number;
  name : string;
  slug : string;
  orderId : string;
  lessons : LessonMetadata[];
}