import {Pipe, PipeTransform} from '@angular/core';
import {Request} from "../../modules/generated/model/request";
import RequestTypeEnum = Request.RequestTypeEnum;

@Pipe({
  name: 'requestType',
  standalone: true
})
export class RequestTypePipe implements PipeTransform {

  transform(value: RequestTypeEnum): string {
    switch (value) {
      case 'FAIRNESS':
        return 'Méltányossági'
      case 'DISMISSAL':
        return 'Jogviszony felmondása'
      case 'SUBJECT':
        return 'Tárgyi ügyek'
      case 'OTHER':
        return 'Egyéb'
    }
  }
}
