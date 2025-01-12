import {Formik, Form, Field} from 'formik';
import css from './SignInForm.module.css';

const initialValues = {
    userName: "",
    password: ""
};

export const SignInForm = () => {
    const handleSubmit = (values:any, actions:any) => {
        console.log(values);
        actions.resetForm();
    };

    return (
        <Formik initialValues={initialValues} onSubmit={handleSubmit}>
            <Form className={css.form}>
                <Field className={css.fInput} type="text" name="userName" placeholder="Email"/>
                <Field className={css.fInput} type="password" name="password" placeholder="Password"/>
                <button className={css.fInput} type="submit">Submit</button>
            </Form>
        </Formik>
    );
};
