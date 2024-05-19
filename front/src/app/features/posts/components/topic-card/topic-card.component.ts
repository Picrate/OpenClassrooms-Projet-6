import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../interfaces/topic";

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent implements OnInit {

  @Input() topic!: Topic

  constructor() {}

  ngOnInit(): void {}

  manageSubscription(topic: Topic) {

  }
}
