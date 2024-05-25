import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../interfaces/topic";
import {UsersService} from "../../../users/services/users.service";

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent implements OnInit {

  @Input() topic!: Topic
  @Input() buttonLabel: string = 'S\'abonner';

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
  }

  manageSubscription(topic: Topic) {
      this.userService.manageTopicSubscription(topic).subscribe();
      this.toggleLabel();
  }

  toggleLabel() {
    this.buttonLabel === "S'abonner" ? this.buttonLabel = "Se désabonner" : this.buttonLabel = "S'abonner";
  }

}
