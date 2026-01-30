import { LessonMetadata } from "./lesson-metadata";

export interface Course{
  id : number;
  name : string;
  slug : string;
  orderId : string;
  lessons: LessonMetadata[];
}