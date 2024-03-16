import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { LoginForm } from 'src/app/shared/interface/authentication/LoginForm';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: UntypedFormGroup;

  constructor(private formBuilder: UntypedFormBuilder) {
    this.form = this.formBuilder.group({
        username: [null],
        password: [null]
    });
  }

  ngOnInit(): void {}

  login() {
    const loginForm = this.form.value as LoginForm;
    console.log(`Usuário: ${loginForm.username}, Senha: ${loginForm.password}`);
  }

}
