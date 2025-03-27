import React, { useState } from 'react';
import { View, TextInput, Button, Text, StyleSheet } from 'react-native';

// Define the type for the props
type MyFormProps = {
  onSubmit?: (data: { name: string; email: string; message: string }) => void;
};

const MyForm: React.FC<MyFormProps> = ({ onSubmit }) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = () => {
    if (onSubmit) {
      onSubmit({ name, email, message });
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.label}>Name:</Text>
      <TextInput style={styles.input} value={name} onChangeText={setName} />
      
      <Text style={styles.label}>Email:</Text>
      <TextInput style={styles.input} value={email} onChangeText={setEmail} keyboardType="email-address" />
      
      <Text style={styles.label}>Message:</Text>
      <TextInput style={styles.input} value={message} onChangeText={setMessage} multiline />
      
      <Button title="Submit" onPress={handleSubmit} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: { padding: 20 },
  label: { fontSize: 16, marginBottom: 5 },
  input: { borderWidth: 1, padding: 10, marginBottom: 10, borderRadius: 5 }
});

export default MyForm;
