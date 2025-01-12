import {Formik, Form, Field} from 'formik';
import css from './SignUpForm.module.css';

const initialValues = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
};

export const SignUpForm = () => {

    const handleSubmit = (values: any, actions: any) => {
        console.log(values);
        actions.resetForm();
    };

    return (
        <Formik initialValues={initialValues} onSubmit={handleSubmit}>
            <Form className={css.form}>
                <Field className={css.fInput} type="text" name="firstName"/>
                <Field className={css.fInput} type="text" name="lastName"/>
                <Field className={css.fInput} type="text" name="email"/>
                <Field className={css.fInput} type="password" name="password"/>
                <Field className={css.fInput} type="confirmPassword" name="password"/>
                <button type="submit">Submit</button>
            </Form>
        </Formik>
    );
};
