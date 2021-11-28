export interface SslSkipPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
