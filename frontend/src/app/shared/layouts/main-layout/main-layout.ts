import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { NavbarCourses } from "../navbar-courses/navbar-courses";
import { NavbarLessons } from "../navbar-lessons/navbar-lessons";

@Component({
  selector: 'app-main-layout',
  imports: [RouterOutlet, Navbar, NavbarCourses, NavbarLessons],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css'
})
export class MainLayout {

}
