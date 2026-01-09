import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../../components/navbar/navbar';
import { NavbarCourses } from "../../components/navbar-courses/navbar-courses";
import { NavbarLessons } from "../../components/navbar-lessons/navbar-lessons";

@Component({
  selector: 'app-main-layout',
  imports: [RouterOutlet, Navbar, NavbarCourses, NavbarLessons],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css'
})
export class MainLayout {

}
