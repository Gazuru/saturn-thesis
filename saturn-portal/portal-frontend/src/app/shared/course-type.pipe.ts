import {Pipe, PipeTransform} from '@angular/core';
import {Course} from "../../modules/generated";
import CourseTypeEnum = Course.CourseTypeEnum;

@Pipe({
  name: 'courseType',
  standalone: true
})
export class CourseTypePipe implements PipeTransform {

  transform(input: CourseTypeEnum): string {
    switch (input) {
      case 'LECTURE':
        return 'Előadás';
      case 'PRACTICE':
        return 'Gyakorlat';
      case 'EXAM':
        return 'Vizsga';
      case 'LABORATORY':
        return 'Laboratórium';
    }
  }
}
