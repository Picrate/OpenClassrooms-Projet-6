import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostsApiService} from "../../services/posts-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {



  public post!: Post;
  commentForm: FormGroup = this.fb.group({comment: ['']});

  constructor(private fb: FormBuilder, private route: ActivatedRoute,private router: Router, private postService: PostsApiService, private location: Location) {}

  ngOnInit(): void {
    this.getPost();
  }

  getPost(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.postService.getById(id || '').subscribe(post => this.post = post);
  }

  goBack(): void {
    this.location.back();
  }

  postComment() {
  }
}
