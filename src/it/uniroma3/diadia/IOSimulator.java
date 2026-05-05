package it.uniroma3.diadia;

public class IOSimulator implements IO {
	private String[] input;
	private int input_count = 0;

	private String[] output;
	private int output_count = 0;
	private int get_output_idx = 0;

	public IOSimulator() {
		this(new String[0]);
	}

	public IOSimulator(String[] input) {
		this.input = input;
		this.output = new String[50];
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.output[this.output_count++] = msg;
	}

	@Override
	public String leggiRiga() {
		return this.input[this.input_count++];
	}

	public String getOutput() {
		if (this.get_output_idx >= this.output_count) {
			return null;
		}

		return this.output[this.get_output_idx++];
	}
}
