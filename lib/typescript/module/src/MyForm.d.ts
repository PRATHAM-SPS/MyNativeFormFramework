import React from 'react';
type MyFormProps = {
    onSubmit?: (data: {
        name: string;
        email: string;
        message: string;
    }) => void;
};
declare const MyForm: React.FC<MyFormProps>;
export default MyForm;
//# sourceMappingURL=MyForm.d.ts.map