import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostsApiService} from "../../services/posts-api.service";
import {Location} from "@angular/common";
import {NewPostComment} from "../../interfaces/new-post-comment";
import {Observable} from "rxjs";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {

  public post!: Post;
  commentForm: FormGroup = this.fb.group({comment: ['']});
  errorMessage: string = '';
  onError: boolean = false;

  constructor(private fb: FormBuilder, private route: ActivatedRoute,private router: Router, private postService: PostsApiService, private location: Location) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if(id !== null){
      this.getPost(id).subscribe(post => {
        this.post = post;
      },
        error => {
          this.onError = true;
          this.errorMessage = error.errorMessage;
        });
    }
  }

  getPost(id: string): Observable<Post> {
      return this.postService.getById(id);
  }

  goBack(): void {
    this.location.back();
  }

  postComment() {
    const newComment: NewPostComment = {
      postId: this.post.id,
      comment: this.commentForm.value.comment
    }
    this.postService.postNewComment(newComment).subscribe(p => {
      this.router.navigate(['/posts/'+this.post.id]);
    })
  }
}
