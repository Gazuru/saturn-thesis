import {Pipe, PipeTransform} from '@angular/core';
import {Course} from "../../modules/generated";
import DayOfWeekEnum = Course.DayOfWeekEnum;

@Pipe({
  name: 'dayOfWeek',
  standalone: true
})
export class DayOfWeekPipe implements PipeTransform {

  transform(input: DayOfWeekEnum): string {
    switch (input) {
      case 'MONDAY':
        return 'Hétfő';
      case 'TUESDAY':
        return 'Kedd';
      case 'WEDNESDAY':
        return 'Szerda';
      case 'THURSDAY':
        return 'Csütörtök';
      case 'FRIDAY':
        return 'Péntek';
      case 'SATURDAY':
        return 'Szombat';
      case 'SUNDAY':
        return 'Vasárnap';
    }
  }
}
