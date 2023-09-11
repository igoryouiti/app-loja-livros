function PasswordValidator(password: string, password2: string): boolean {
    
    if(password === password2 && password.length > 8){
        const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{8,}$/;
        return regex.test(password);
    } else 
        return false;
}

export default PasswordValidator