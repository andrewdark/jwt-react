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
                <Field type="text" name="userName" />
                <Field type="password" name="password" />
                <button type="submit">Submit</button>
            </Form>
        </Formik>
    );
};
