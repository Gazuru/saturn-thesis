import {Pipe, PipeTransform} from '@angular/core';
import {Request} from "../../modules/generated/model/request";
import StatusEnum = Request.StatusEnum;

@Pipe({
  name: 'requestStatus',
  standalone: true
})
export class RequestStatusPipe implements PipeTransform {

  transform(value: StatusEnum): string {
    switch (value) {
      case "NEW":
        return "Új";
      case "IN_PROGRESS":
        return "Folyamatban";
      case "REQUESTER_FEEDBACK":
        return "Kérvényező visszajelzés";
      case "IN_REVIEW":
        return "Felülvizsgálat alatt";
      case "ACCEPTED":
        return "Elfogadva";
      case "DENIED":
        return "Megtagadva";
    }
  }
}
