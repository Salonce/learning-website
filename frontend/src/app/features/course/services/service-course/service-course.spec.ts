import { TestBed } from '@angular/core/testing';

import { ServiceCourse } from './service-course';

describe('ServiceCourse', () => {
  let service: ServiceCourse;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceCourse);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
